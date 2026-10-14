Feature: User
  @CreateUser
  Scenario: Crear usuario y ejecutar CRUD de proyecto con TOKEN
    When send POST request "api/user.json" with
    """
    {
      "Email":"grupo11@ucb.com",
      "FullName":"Grupo11",
      "Password":"ucb123"
    }
    """
    Then code should be 200
    And the "Email" should be "grupo11@ucb.com"
    And the "FullName" should be "Grupo11"
    And save "Id" in "USER_ID"

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