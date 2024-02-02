pipeline {
    agent any

    parameters {
        string defaultValue: 'http://127.0.0.1', name:'baseUrl'
        choice choices: ['api', 'ui'], name:'suite'
        booleanParam defaultValue: false, name: 'local'
    }

    tools {
        maven 'maven'
        jdk 'JDK_17'
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn --version'
                sh 'java --version'
                echo "Build number $env.BUILD_NUMBER"
                sh "mvn clean test -DbaseURL=$baseURL -Dsuite=$suite -Dlocal=$local"
            }

            post {
                always{
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results:[[path: 'target/allure-results']]
                        ])
                }
            }
        }
    }
}