# To perform vehicle lookup using car registration number
@test
Feature: Vehicle Lookup using car registration number
  This feature will test vehicle lookup for multiple car registration number combination

  @Scenario1
  Scenario Outline: User searches for vehicle details with existing car registration number
    Given user launches the dealer portal
    When user performs Vehicle lookup for "<VehicleRegNum>"
    Then results for "<VehicleRegNum>" should be displayed

    Examples: 
      | VehicleRegNum |
      | OV12UYY       |
  
  @Scenario2
  Scenario Outline: User searches for vehicle details with non existing car registration number
    Given user launches the dealer portal
    When user performs Vehicle lookup for "<VehicleRegNum>"
    Then no records should be displayed

    Examples: 
      | VehicleRegNum |
      | OV12UYZ       | 
      | OV12UYY123 	  |   
      | 123      	  |              
  
  @Scenario3
  Scenario Outline: User searches for vehicle details with invalid car registration number
    Given user launches the dealer portal
    When user performs Vehicle lookup for "<VehicleRegNum>"
    Then error message should be displayed

    Examples: 
      | VehicleRegNum |
      | %^      	  |  
      | 	      	  |  