pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn build'
            }
        }
        stage('StopService') {
            steps {
                sh 'chmod +x stopService.sh'
                sh './stopService.sh'
            }
        }
        stage('Deploy') {
            steps {
                sh 'java -jar /var/jenkins_home/workspace/api-jenkins/target/demo-0.0.1-SNAPSHOT.jar --server.port=8081'
            }
        }
    }
}
