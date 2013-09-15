package org.anvard.introtojava.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.anvard.introtojava.Person;
import org.apache.commons.lang.RandomStringUtils;

import com.google.caliper.Benchmark;
import com.google.caliper.runner.CaliperMain;

/**
 * There are lots of collection benchmarks out there, but
 * most of them are about squeezing the maximum performance
 * out of particular use cases - in other words, cases where
 * performance is already being measured. This class exists
 * to show what happens under the more normal case where
 * standard collections are used, maybe incorrectly, and no
 * one notices until the system scales.
 * 
 * @author alan
 *
 */
public class CollectionsBenchmark extends Benchmark {

	private List<Person> personArrayList;
	private List<Person> personLinkedList;
	private Map<Integer,Person> personHashMap;
	private Map<String,Person> personLastNameIndex; 
	private Random r;

	private Person randomPerson(int id) {
		return new Person(id, RandomStringUtils.random(8), RandomStringUtils.random(12));
	}
	
	@Override
	protected void setUp() throws Exception {
		personArrayList = new ArrayList<>();
		personLinkedList = new LinkedList<>();
		personHashMap = new HashMap<>();
		personLastNameIndex = new HashMap<>();
		r = new Random();
		// Initialize collections before we time them
		for (int i = 0; i < 10_000; i++) {
			Person p = randomPerson(i);
			personArrayList.add(p);
			personLinkedList.add(p);
			personHashMap.put(i, p);
			personLastNameIndex.put(p.getLastName(), p);
		}
	}
	
	public String timeArrayListIteration(int reps) {
		String name = null;
		for (int i = 0; i < reps; i++) {
			for (Person p: personArrayList) {
				name = p.getLastName();
			}
		}
		return name;
	}

	public String timeLinkedListIteration(int reps) {
		String name = null;
		for (int i = 0; i < reps; i++) {
			for (Person p: personLinkedList) {
				name = p.getLastName();
			}
		}
		return name;
	}

	public String timeHashMapIteration(int reps) {
		String name = null;
		for (int i = 0; i < reps; i++) {
			for (Person p: personHashMap.values()) {
				name = p.getLastName();
			}
		}
		return name;
	}
	
	public String timeArrayListFetch(int reps) {
		String name = null;
		int randomItem = r.nextInt(personArrayList.size());
		for (int i = 0; i < reps; i++) {
			name = personLinkedList.get(randomItem).getLastName();
		}
		return name;
	}

	public String timeLinkedListFetch(int reps) {
		String name = null;
		int randomItem = r.nextInt(personArrayList.size());
		for (int i = 0; i < reps; i++) {
			name = personHashMap.get(randomItem).getLastName();
		}
		return name;
	}
	
	private String randomLastName() {
		int randomItem = r.nextInt(personArrayList.size());
		return personArrayList.get(randomItem).getLastName();
	}
	
	public int timeArrayListSearch(int reps) {
		int id = 0;
		String name = randomLastName();
		for (int i = 0; i < reps; i++) {
			for (Person p: personArrayList) {
				if (name.equals(p.getLastName())) {
					id = p.getId();
					break;
				}
			}
		}
		return id;
	}

	public int timeLinkedListSearch(int reps) {
		int id = 0;
		String name = randomLastName();
		for (int i = 0; i < reps; i++) {
			for (Person p: personLinkedList) {
				if (name.equals(p.getLastName())) {
					id = p.getId();
					break;
				}
			}
		}
		return id;
	}

	public int timeHashMapNoIndexSearch(int reps) {
		int id = 0;
		String name = randomLastName();
		for (int i = 0; i < reps; i++) {
			for (Person p: personHashMap.values()) {
				if (name.equals(p.getLastName())) {
					id = p.getId();
					break;
				}
			}
		}
		return id;
	}

	public int timeHashMapWithIndexSearch(int reps) {
		int id = 0;
		String name = randomLastName();
		for (int i = 0; i < reps; i++) {
			id = personLastNameIndex.get(name).getId();
		}
		return id;
	}

	public static void main(String args[]) {
		CaliperMain.main(CollectionsBenchmark.class, args);
	}
	
}
