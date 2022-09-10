pipeline {
    agent any
     tools { 
        maven '3.8.5' 
        jdk '8' 
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install'
            }
        }
        stage('StopService') {
            steps {
                script{
                     for pid in `ps aux | grep FACCAT-G2-SEGURANCA | awk '{print $2}'`
                    do
                        kill $pid
                    done
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'java -jar /var/jenkins_home/workspace/api-jenkins/target/demo-0.0.1-SNAPSHOT.jar --server.port=8081'
            }
        }
    }
}
