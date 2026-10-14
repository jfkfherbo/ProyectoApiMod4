Feature: Project
  @Regression
  Scenario:  CRUD Project

    Given tengo acceso a Todo.ly
    #create
    When send POST request "api/projects.json" with
    """
    {
      "Content":"CucumberAPI"
    }
    """
    Then code should be 200
    And the "Content" should be "CucumberAPI"
    And save "Id" in "ID"

    #read
    When send GET request "api/projects/ID.json" with
    """
    """
    Then code should be 200
    And the "Content" should be "CucumberAPI"

    #update
    When send PUT request "api/projects/ID.json" with
    """
    {
      "Content":"CucumberAPIUpdate"
    }
    """
    Then code should be 200
    And the "Content" should be "CucumberAPIUpdate"

    #delete
    When send DELETE request "api/projects/ID.json" with
    """
    """
    Then code should be 200
    And the "Content" should be "CucumberAPIUpdate"

