package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTO().groupPage();
    if (app.group().all().size() ==0){
      app.group().create(new GroupData().withName("test1").withFooter("test2").withHeader("test3"));
    }
  }

  @Test
  public void testGroupDeletion() {
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next(); //возвращает 1-й попавшийся эл-т множества
    app.group().delete(deletedGroup);
    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size() - 1)) ;
    //before.remove(deletedGroup);
    assertThat(after, equalTo(before.withOut(deletedGroup)));

    //Assert.assertEquals(after, before);
    //сравниваем списки way one
    /*
    for (int i = 0; i < after.size(); i++){
      Assert.assertEquals(after.get(i), before.get(i));
    } */
    //way two

    //System.out.println("after = " + after + "; " + "before = " + before);
  }

  }
