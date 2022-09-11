pipeline {
    agent any
    stages {
        stage('Docker build') {
            steps {
                sh 'docker build -t appfaccat/1.0 .'
            }
        }
        stage('Docker run') {
            steps {
                sh 'docker run -p 8080:8082 --network newnetwork appfaccat/1.0 .'
               
            }
        }
       
    }
}
