package ru.ivanmurzin.falloutdungeon;

import org.junit.Test;

import ru.ivanmurzin.falloutdungeon.tools.GameList;

public class GameListTest {
    @Test
    public void addition() {
        GameList<String> list = new GameList<>();
        assert list.size() == 0;
        list.add("1");
        list.add("2");
        list.add("3");
        assert list.size() == 3;
        list.add("4");
        list.add("5");
        list.add("6");
        assert list.size() == 6;
    }

    @Test
    public void getting() {
        GameList<String> list = new GameList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        assert list.get(0).equals("1");
        assert list.get(1).equals("2");
        assert list.get(2).equals("3");
        assert list.get(3).equals("4");
        assert list.get(4).equals("5");
        assert list.get(5).equals("6");

        try {
            list.get(10);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
            assert exception.getMessage().equals("size: 6 index: 10");
        }
    }

    @Test
    public void removing() {
        GameList<String> list = new GameList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        list.remove("6");
        list.remove("5");
        list.remove("4");
        list.remove("2");
        list.remove("1");
        list.remove("lol");
        list.remove("kek");
        assert list.size() == 1;
        list.remove("3");
        assert list.isEmpty();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        assert list.get(0).equals("1");
        assert list.get(1).equals("2");
        assert list.get(2).equals("3");
        assert list.get(3).equals("4");
        assert list.get(4).equals("5");
        assert list.get(5).equals("6");

        list.remove("3");

        assert list.get(2).equals("4");

        list.clear();

    }

    @Test
    public void iterator() {
        GameList<Integer> list = new GameList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        int sum = 0;
        for (Integer integer : list) {
            sum += integer;
        }

        assert sum == 21;

        list.removeIf(integer -> integer == 3);
        sum = 0;
        for (Integer integer : list) {
            sum += integer;
        }

        assert sum == 18;
    }


    @Test
    public void addingAll() {
        GameList<Integer> list1 = new GameList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        list1.add(6);
        GameList<Integer> list2 = new GameList<>();
        list2.add(7);
        list2.add(8);
        list2.add(9);
        list1.addAll(list2);
        int sum = 0;
        for (Integer integer : list1) {
            sum += integer;
        }

        assert sum == 45;
    }


}