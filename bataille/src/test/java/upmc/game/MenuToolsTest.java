package upmc.game;

import static org.junit.Assert.*;

import org.junit.*;

import java.util.Optional;

public class MenuToolsTest {

  private MenuTools tools;
  
  @Before
  public void createMenuTools() {
    tools = new MenuTools();
  }
  
  @Test
  public void testParseMenuChoice() {
    String choice = "";
    Optional<Integer> result = tools.parseMenuChoice(choice);
    assertNotNull(result);
}

  @Test
  public void testVerifyMenuChoice() {
    // Arrange
    int menu = 1;
    int maxEntries = 3;
    // Act
    boolean result = tools.verifyMenuChoice(menu, maxEntries);
    // Assert
    assertTrue(result);
  }
  
  @Test(expected=BadMenuChoiceException.class)
  public void testVerifyMenuChoiceException() {
    // Arrange
    int menu = 0;
    int maxEntries = 2;
    // Act
    tools.verifyMenuChoiceException(menu, maxEntries);
  }
}
