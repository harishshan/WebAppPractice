mainApp.controller('studentController', function($scope,$http) {
	$scope.student = {
		firstName: "Harish",
		lastName: "Shanmugam",
		subjects: [
		           {name:'Tamil',code:'TAM', mark:100},
		           {name:'English',code:'ENG',mark:98},
		           {name:'Maths',code:'MAT',mark:99},
		           {name:'Science',code:'SCI',mark:88},
		           {name:'Social Science',code:'SS',mark:93}
		          ],
		 total: function() {
			var studentObject;
			studentObject = $scope.student;
			var totalMarks=0;
			for(var i=0;i<studentObject.subjects.length;i++){
				totalMarks += studentObject.subjects[i].mark;
			}
			return totalMarks;
		},
		fees:300,
	};
	var url = "data.txt";
	$http.get(url).success( function(response) {
		$scope.message = response; 
	});
});
/*
mainApp.controller('AddStudentController', function($scope) {
    $scope.message = "This page will be used to display add student form";
 });
 
mainApp.controller('ViewStudentsController', function($scope) {
    $scope.message = "This page will be used to display all the students";
 });*/