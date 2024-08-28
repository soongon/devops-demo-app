pipeline {
    agent any

    environment {
        SSH_SERVER = 'docker-host' // Publish over SSH 플러그인에서 설정한 SSH 서버 이름
        REMOTE_DEPLOY_DIR = 'deployment' // WAR 파일을 전송할 원격 디렉토리
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/soongon/devops-demo-app.git'
            }
        }

        stage('Build') {
            steps {
                withMaven(maven: 'maven3') { // 'maven3'은 Jenkins에서 설정한 Maven 설치 이름
                    sh 'mvn clean package'
                }
            }
        }

        stage('Deploy') {
            steps {
                // WAR 파일 전송
                sshPublisher(
                    publishers: [
                        sshPublisherDesc(
                            configName: SSH_SERVER,
                            transfers: [
                                sshTransfer(
                                    sourceFiles: '**/*.war',
                                    removePrefix: 'target',
                                    remoteDirectory: REMOTE_DEPLOY_DIR,
                                    execCommand: """
                                        cd ${REMOTE_DEPLOY_DIR} || exit 1;
                                        docker stop tomcat-stage || true;
                                        docker rm tomcat-stage || true;
                                        docker rmi my-tomcat || true;
                                        docker build -t my-tomcat . || exit 1;
                                        docker run -d --name tomcat-stage -p 8080:8080 my-tomcat || exit 1;
                                        rm *.war || exit 1;
                                    """
                                )
                            ]
                        )
                    ]
                )
            }
        }
    }

    post {
        success {
            echo 'Deployment was successful!'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}
