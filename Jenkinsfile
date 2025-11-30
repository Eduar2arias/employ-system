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

        stage('Start services') {
            steps {
                sh 'docker compose up -d mysql backend tests'
            }
        }

        stage('Run tests') {
            steps {
                sh 'docker exec test-runner mvn test'
            }
        }

        stage('Generate coverage') {
            steps {
                sh 'docker exec test-runner mvn jacoco:report'
            }
        }

        stage('Deploy frontend') {
            steps {
                sh 'docker compose up -d frontend'
            }
        }
    }

    post {
        always {
            sh 'docker compose down'
        }
    }

   /* post {
        always {
            sh 'docker compose ps'
        }
    }
    */
}
