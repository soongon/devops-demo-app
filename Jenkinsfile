pipeline {
    agent any

    environment {
        GIT_REPO = 'https://github.com/soongon/devops-demo-app.git'
        BRANCH = 'master'
        MAVEN_GOALS = 'clean package'  // Maven 빌드 명령어
        TOMCAT_CREDENTIALS_ID = 'tomcat-deployer'  // Tomcat 서버 자격 증명 ID
        TOMCAT_URL = 'http://43.203.102.244:8080'  // Tomcat 서버 URL
    }

    stages {
        stage('Checkout') {
            steps {
                // GitHub에서 소스 코드 체크아웃
                git branch: "${BRANCH}", url: "${GIT_REPO}"
            }
        }

        stage('Build') {
            steps {
                // Maven 빌드 수행
                withMaven(maven: 'maven3') {  // Jenkins에 설정된 Maven 설치 이름, Pipleline Maven Integration 플러그인 설치 필요
                    sh "mvn ${MAVEN_GOALS}"
                }
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                script {
                    // "Deploy WAR/EAR to a container" 플러그인 으로 톰캣서버에 디플로이
                    deploy adapters:
                           [tomcat9(credentialsId: "${TOMCAT_CREDENTIALS_ID}", url: "${TOMCAT_URL}")],
                           contextPath: '/app',
                           war: '**/target/*.war'
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment completed successfully!'
        }
        failure {
            echo 'Deployment failed.'
        }
    }
}