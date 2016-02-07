'use strict';

App.controller('TimeTrackerController', ['$scope', 'TimeTrackerService', function($scope, TimeTrackerService) {
          var self = this;
          self.compconfig={id:null,computerName:'',date:'',totalTime:''};
          self.compconfigs=[];
              
          self.fetchData = function(TimeTrackerService,param1,param2){
        	  TimeTrackerService.fetchData(param1,param2)
                  .then(
      					       function(d) {
      						        self.compconfigs = d;
      					       },
            					function(errResponse){
            						console.error('Error while fetching time data');
            					}
      			       );
          };
           
          /*self.createUser = function(user){
              UserService.createUser(user)
		              .then(
                      self.fetchAllUsers, 
				              function(errResponse){
					               console.error('Error while creating User.');
				              }	
                  );
          };

         self.updateUser = function(user, id){
              UserService.updateUser(user, id)
		              .then(
				              self.fetchAllUsers, 
				              function(errResponse){
					               console.error('Error while updating User.');
				              }	
                  );
          };

         self.deleteUser = function(id){
              UserService.deleteUser(id)
		              .then(
				              self.fetchAllUsers, 
				              function(errResponse){
					               console.error('Error while deleting User.');
				              }	
                  );
          };*/

          self.fetchData(TimeTrackerService,'sarwesh', '05-Feb-2016');
          
          self.search = function(param1,param2) {
        	  self.fetchData(TimeTrackerService,param1, param2);
              self.reset();
          };

          /*self.submit = function() {
              if(self.user.id==null){
                  console.log('Saving New User', self.user);    
                  self.createUser(self.user);
              }else{
                  self.updateUser(self.user, self.user.id);
                  console.log('User updated with id ', self.user.id);
              }
              self.reset();
          };
              
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.users.length; i++){
                  if(self.users[i].id == id) {
                     self.user = angular.copy(self.users[i]);
                     break;
                  }
              }
          };
              
          self.remove = function(id){
              console.log('id to be deleted', id);
              for(var i = 0; i < self.users.length; i++){
                  if(self.users[i].id == id) {
                     self.reset();
                     break;
                  }
              }
              self.deleteUser(id);
          };
*/
          
          self.reset = function(){
        	  self.compconfig={id:null,computerName:'',date:'',totalTime:''};
              $scope.myForm.$setPristine(); //reset Form
          };

      }]);
