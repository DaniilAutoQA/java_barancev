package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTO().groupPage();
        if (app.group().all().size() ==0 ){
            app.group().create(new GroupData().withName("test1").withFooter("test2").withHeader("test3"));
        }
    }

    @Test
    public void testModificationGroup(){
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("homeGroup").withHeader("test2").withFooter("test3");
        app.group().modify(group);
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size()));
        //before.remove(modifiedGroup);
        //before.add(group);
        //Assert.assertEquals(before, after);
        assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
        System.out.println("after = " + after + "; " + "before = " + before);

    }
}
