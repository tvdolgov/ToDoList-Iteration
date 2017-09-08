<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="en-US">
<head>
    <meta charset="UTF-8" />
    <title> Task Manager </title>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <style>
        .text-bold {font-weight: bold;}

    </style>
    <script type="text/javascript">
        var myApp = angular.module('TaskManagement', []);
        myApp.controller('TaskController', function($scope, $http){
            $scope.tasks = [];
            $scope.taskForm = {
                id : -1,
                creationDate : "",
                finishDate : "",
                description: ""
            };

            _refreshTaskData();

            $scope.submitTask = function() {
                var method = "";
                var url = "";
                if ($scope.taskForm.id == -1) {
                    method = "PUT";
                    url = 'http://localhost:8080/task/add';
                } else {
                    method = "POST";
                    url = 'http://localhost:8080/task/update';
                }
                $http({
                    method : method,
                    url : url,
                    data : angular.toJson($scope.taskForm),
                    headers : {
                        'Content-Type' : 'application/json'
                    }
                }).then( _success, _error );
            };

            $scope.deleteTask = function(task) {
                $http({
                    method : 'DELETE',
                    url : 'http://localhost:8080/task/delete/' + task.id
                }).then(_success, _error);
            };

            $scope.editTask = function(task) {
                $scope.taskForm.finishDate = task.finishDate;
                $scope.taskForm.description = task.description;
                $scope.taskForm.creationDate = task.creationDate;
                $scope.taskForm.id = task.id;
            };

            function _refreshTaskData() {
                $http({
                    method : 'GET',
                    url : 'http://localhost:8080/task/all'
                }).then(function successCallback(response) {
                    $scope.tasks = response.data;
                }, function errorCallback(response) {
                    console.log(response.statusText);
                });
            }

            function _success(response) {
                _refreshTaskData();
                _clearFormData()
            }

            function _error(response) {
                console.log(response.statusText);
            }

            function _clearFormData() {
                $scope.taskForm.id = -1;
                $scope.taskForm.creationDate = "";
                $scope.taskForm.finishDate = "";
                $scope.taskForm.description = "";
            };

            $scope.dateClass = function(value) {
                console.log(value, Date.now())
                if(value < Date.now()) {
                    return "text-bold"
                }
                return ""
            }
        });
    </script>
</head>
<body ng-app="TaskManagement" ng-controller="TaskController">
<div class="container">
    <div align="center" class="page-header"><h1>Task Manager</h1></div>
    <form ng-submit="submitTask()">
        <h3>Add/Edit Task</h3>
        <div class="form-group">
            <div class="input-group">
                <div>Finish Date</div>
                <div>
                    <input type="date" ng-model="taskForm.finishDate"/>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <div>Description</div>
                <div>
                    <input type="text" size="128" ng-model="taskForm.description" maxlength="128"/>
                </div>
            </div>
        </div>
        <div>
            <input type="submit" value="Submit" class="btn btn-primary" />
        </div>
    </form>
    <h2></h2>
    <table class="table">
        <thead class="thead-default">
            <tr align="center">
                <th>ID</th>
                <th>Creation Date</th>
                <th>Finish Date</th>
                <th>Description</th>
            </tr>
        </thead>
    <tr ng-repeat="task in tasks">
        <td>{{ task.id }}</td>
        <td><span>{{ task.creationDate | date:"MM/dd/yyyy 'at' h:mma" }}</span></td>
        <td ng-class="dateClass(task.finishDate)")>
            <span> {{ task.finishDate | date:"MM/dd/yyyy 'at' h:mma" }} </span>
        </td>
        <td>{{ task.description }}</td>
        <td><a ng-click="editTask(task)" class="btn btn-success">Edit</a>    <a
                ng-click="deleteTask(task)" class="btn btn-danger">Delete</a></td>
    </tr>

    </table>
</div>
</body>
</html>