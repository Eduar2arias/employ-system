pipeline {
    agent any

    environment {
        DOCKER_HOST = 'unix:///var/run/docker.sock'
    }

    stages {

        stage('Construir imágenes') {
            steps {
                sh 'docker compose build'
            }
        }


        stage('Desplegar') {
            steps {
                // sh 'docker compose up -d'
                sh 'docker compose up -d mysql backend frontend'
            }
        }
    }

    post {
        always {
            sh 'docker compose ps'
        }
    }
}
