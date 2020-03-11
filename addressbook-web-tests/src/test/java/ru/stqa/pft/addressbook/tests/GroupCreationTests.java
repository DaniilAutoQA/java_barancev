package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("test4", "test2", "test3");
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size() + 1);
    before.add(group);
    //поиск максимального id
    /*
    int max = 0;
    for (GroupData g: after){
      if (g.getId()> max){
        max=g.getId();
      }
    } */
    ////поиск максимального id с помощью лямбда выражения
  //  group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    // Сортируем и затем проверяем списки
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    Assert.assertEquals(before, after);
   }
}
