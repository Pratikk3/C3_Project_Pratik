import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {


    Restaurant restaurant;


    @BeforeEach
    void setUp() {

        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        Restaurant restaurant1 = Mockito.spy(restaurant);

    }
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){

        Restaurant restaurant1 = Mockito.spy(restaurant);
        LocalTime betweenTime = LocalTime.parse("11:30:00");
        when(restaurant1.getCurrentTime()).thenReturn(betweenTime);
        assertTrue(restaurant1.isRestaurantOpen());
        //WRITE UNIT TEST CASE HERE
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){

        Restaurant restaurant1 = Mockito.spy(restaurant);
        LocalTime outsideTime = LocalTime.parse("09:00:00");
        when(restaurant1.getCurrentTime()).thenReturn(outsideTime);
        assertFalse(restaurant1.isRestaurantOpen());
        //WRITE UNIT TEST CASE HERE
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();

        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {


        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void adding_items_should_return_amount_anything_other_than_0(){
        List<Item> items = new ArrayList<>();
        items.add(new Item("Noodles",50));
        items.add(new Item("Pasta",150));
        items.add(new Item("Lasagne",250));
        double amount = restaurant.getAmountForItems(items);
        assertEquals(450,amount);
    }

    @Test
    public void adding_no_items_to_list_should_return_amount_as_0(){
        List<Item> items = new ArrayList<>();
        double amount = restaurant.getAmountForItems(items);
        assertEquals(0,amount);
    }


}