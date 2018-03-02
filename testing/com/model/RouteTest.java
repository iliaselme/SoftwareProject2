package com.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.hamcrest.collection.IsEmptyCollection;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;



public class RouteTest {

private Route route;
	
	@Before
	public void setUp() throws Exception {
		route = new Route();
	}
	
	@Test
	public void testSetAfstand() {
		final double DELTA = 1e-15;
		route.setAfstand(1.5);
		assertEquals(1.5, route.getAfstand(), DELTA);
		route.setAfstand(-100); //-----------------------mag dit?----------------
		assertEquals(-100, route.getAfstand(), DELTA);
		route.setAfstand(0); //-----------------------mag dit?----------------
		assertEquals(0, route.getAfstand(), DELTA);
	}
	
	@Test
    public void testPerronsList() {

        List<Integer> perrons = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);

        //1. Test equal.
        assertThat(perrons, is(expected));

        //2. Check List has this value
        assertThat(perrons, hasItems(2));

        //3. Check List Size
        assertThat(perrons, hasSize(5));
        assertThat(perrons.size(), is(5));

        //4.  List order

        // Ensure Correct order
        assertThat(perrons, contains(1, 2, 3, 4, 5));

        // Can be any order
        assertThat(perrons, containsInAnyOrder(5, 4, 3, 2, 1));

        //5. check empty list
        assertThat(perrons, not(IsEmptyCollection.empty()));
        assertThat(new ArrayList<>(), IsEmptyCollection.empty());
    }
	
//	@Test
//    public void testStationsList() {
//
//        List<Station> stations = Arrays.asList(1, 2, 3, 4, 5);
//        List<Station> expected = Arrays.asList(1, 2, 3, 4, 5);
//
//        //1. Test equal.
//        assertThat(stations, is(expected));
//
//        //2. Check List has this value
//        //assertThat(stations, hasItems(2));
//
//        //3. Check List Size
//        assertThat(stations, hasSize(5));
//        assertThat(stations.size(), is(5));
//
//        //4.  List order
//
//        // Ensure Correct order
//        assertThat(stations, contains(1, 2, 3, 4, 5));
//
//        // Can be any order
//        assertThat(stations, containsInAnyOrder(5, 4, 3, 2, 1));
//
//        //5. check empty list
//        assertThat(stations, not(IsEmptyCollection.empty()));
//        assertThat(new ArrayList<>(), IsEmptyCollection.empty());
//    }
	

}
