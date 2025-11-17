pipeline {
    agent any

    environment {
        DOCKER_HOST = 'unix:///var/run/docker.sock'
    }

    stages {
        stage('Build images') {
            steps {
                sh 'docker compose build'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    docker compose down || true
                    docker compose up -d mysql backend frontend
                '''
            }
        }
    }

   /* post {
        always {
            sh 'docker compose ps'
        }
    }
    */
}
