pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/juliakos12/devops3.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def dockerImage = docker.build('dockerimage', '-f Dockerfile .')
                    dockerImage.push()
                }
            }
        }

        stage('Cleanup') {
            steps {
                sh 'docker rmi dockerimage'
            }
        }
    }
}