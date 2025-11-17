pipeline {
    agent any
    
    stages {
        stage('Construir imágenes') {
            steps {
                echo 'Building Docker images...'
                script {
                    // Verificar que docker compose está disponible
                    sh 'docker compose version'
                    sh 'docker compose build --no-cache'
                }
            }
        }
        
        stage('Desplegar') {
            steps {
                echo 'Deploying application...'
                sh 'docker compose down || true'
                sh 'docker compose up -d'
            }
        }
        
        stage('Verificar') {
            steps {
                echo 'Verifying deployment...'
                sleep time: 30, unit: 'SECONDS'
                sh 'docker compose ps'
                sh 'curl -f http://backend:8080/actuator/health || echo "Backend check failed"'
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline execution completed'
            sh 'docker ps -a'
        }
    }
}