package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTO().groupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("test4").withFooter("test2").withHeader("test3");
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(),before.size() + 1);

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
    // Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
   // before.sort(byId);
   // after.sort(byId);
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    //проверка множества без сортировки
    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()); //группе присваеваем max id возвращает max id
    before.add(group);
    Assert.assertEquals(before, after);
   }
}
