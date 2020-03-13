package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

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
    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next(); //возвращает 1-й попавшийся эл-т множества
    app.group().delete(deletedGroup);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(deletedGroup);
    //сравниваем списки way one
    /*
    for (int i = 0; i < after.size(); i++){
      Assert.assertEquals(after.get(i), before.get(i));
    } */
    //way two
    Assert.assertEquals(after, before);
    //System.out.println("after = " + after + "; " + "before = " + before);
  }

  }
