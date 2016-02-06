'use strict';

App.factory('TimeTrackerService', ['$http', '$q','name', 'date', function($http, $q,name,date){

	return {
		
			fetchData: function(name,date) {
				url = '/timetracker/';
				if(name != '' && date != '')
				{
					url = url + 'find/' + name + '/' + date;
				}
				else if(name != '' && date == '')
				{
					url = url + 'findbyname/' + name ;
				}
				else if(name == '' && date != '')
				{
					url = url + 'findbydate/' + date ;
				}
				else
				{
					url = url + 'findall';
				}
					return $http.get(url)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching computerdata');
										return $q.reject(errResponse);
									}
							);
			}
			/*,
		    
		    createUser: function(user){
					return $http.post('http://localhost:8080/Spring4MVCAngularJSExample/user/', user)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating user');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateUser: function(user, id){
					return $http.put('http://localhost:8080/Spring4MVCAngularJSExample/user/'+id, user)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating user');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteUser: function(id){
					return $http.delete('http://localhost:8080/Spring4MVCAngularJSExample/user/'+id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting user');
										return $q.reject(errResponse);
									}
							);
			}*/
		
	};

}]);
