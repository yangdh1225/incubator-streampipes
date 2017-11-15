valueSpecification.$inject = [];

export default function valueSpecification() {

	return {
		restrict : 'E',
		templateUrl : 'app/sensors/directives/general/value-specification.tmpl.html',
		scope : {
			disabled : "=",
			property : "=",
			runtimeType :"="
		},
		controller: function($scope, $element)  {

			$scope.valueSpecifications = [{label : "None", "type" : undefined},
				{label : "Quantitative Value", "type" : "org.streampipes.model.schema.QuantitativeValue"},
				{label : "Enumeration", "type" : "org.streampipes.model.schema.Enumeration"}];


			$scope.isDisallowed = function(type) {
				if ((type == $scope.valueSpecifications[1].type) && !isNumericalProperty()) return true;
				else if ((type == $scope.valueSpecifications[2].type) && isBoolean()) return true;
				else return false;
			}

			var isNumericalProperty = function() {
				if ($scope.runtimeType != "http://www.w3.org/2001/XMLSchema#string" && 
					$scope.runtimeType != "http://www.w3.org/2001/XMLSchema#boolean") return true;
			else return false;

			}

			var isBoolean = function() {
				if ($scope.runtimeType == "http://www.w3.org/2001/XMLSchema#boolean") return true;
				else return false;
			}

			$scope.add = function() {
				if ($scope.property.properties == undefined) {
					$scope.property.properties = {};
					$scope.property.properties.runtimeValues = [];
				}
				$scope.property.properties.runtimeValues.push("");
			}

			$scope.remove = function(runtimeValues, propertyIndex) {
				runtimeValues.splice(propertyIndex, 1);
			};
		}
	}
};
