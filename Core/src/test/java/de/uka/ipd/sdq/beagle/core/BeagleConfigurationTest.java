package de.uka.ipd.sdq.beagle.core;

import static de.uka.ipd.sdq.beagle.core.testutil.ExceptionThrownMatcher.throwsException;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import de.uka.ipd.sdq.beagle.core.testutil.ThrowingMethod;
import de.uka.ipd.sdq.beagle.core.testutil.factories.TestFileFactory;

import org.junit.Before;
import org.junit.Test;
import org.palladiosimulator.pcm.core.entity.Entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests {@link BeahgleConfiguration} and contains all test cases needed to check every
 * method.
 *
 * @author Michael Vogt
 */

public class BeagleConfigurationTest {

	/**
	 * A {@link TestFileFactory} providing methods to get files to use for tests.
	 */
	private static final TestFileFactory TEST_FILE_FACTORY = new TestFileFactory();

	/**
	 * A list of elements providing methods to get elements to use for tests.
	 */
	private List<Entity> elements;

	/**
	 * Mock an initialised element list.
	 */
	@Before
	public void initialiseElements() {
		this.elements = new ArrayList<>();
		this.elements.add(mock(Entity.class));
	}

	/**
	 * Test method for {link BeahgleConfiguration#Beagleconfiguration( List,
	 * java.io.File)}.
	 *
	 */
	@Test
	public void constructor() {
		final File[] files = TEST_FILE_FACTORY.getAll();

		final File notExistingFile = new File("/de/uka/ipd/sdq/beagle/core/NotExisting.java");
		ThrowingMethod method = () -> {
			new BeagleConfiguration(this.elements, notExistingFile);
		};
		assertThat("repositoryFile must exist.", method, throwsException(IllegalArgumentException.class));

		method = () -> {
			new BeagleConfiguration(null, files[0]);
		};
		assertThat("elements must not be null.", method, throwsException(NullPointerException.class));

		method = () -> {
			new BeagleConfiguration(this.elements, null);
		};
		assertThat("repositoryFile must not be null.", method, throwsException(NullPointerException.class));

		new BeagleConfiguration(this.elements, files[0]);

		final BeagleConfiguration beagleConfig = new BeagleConfiguration(this.elements, files[0]);
		beagleConfig.setElements(this.elements);
		final List<Entity> copyedElements = new ArrayList<>();
		copyedElements.addAll(this.elements);
		this.elements.clear();
		assertThat("The elements must be copyed.", beagleConfig.getElements(), is(copyedElements));

	}

	/**
	 * Test method for {@link BeagleConfiguration#getElements()}.
	 */
	@Test
	public void getElements() {
		final File[] files = TEST_FILE_FACTORY.getAll();
		final File file = files[0];
		final BeagleConfiguration beagleConfig = new BeagleConfiguration(this.elements, file);
		assertThat(beagleConfig.getElements(), is(sameInstance(this.elements)));
	}

	/**
	 * Test method for {@link BeagleConfiguration#setElements()}.
	 */
	@Test
	public void setElements() {
		final File[] files = TEST_FILE_FACTORY.getAll();
		final File file = files[0];
		final BeagleConfiguration beagleConfig = new BeagleConfiguration(this.elements, file);
		beagleConfig.setElements(this.elements);
		assertThat(beagleConfig.getElements(), is(sameInstance(this.elements)));
	}

	/**
	 * Test method for {@link BeagleConfiguration#setTimeout()} and
	 * {@link BeagleConfiguration#getTimeout()}.
	 */
	@Test
	public void timeoutTest() {
		final int testTimeout = 42;
		final File[] files = TEST_FILE_FACTORY.getAll();
		final File file = files[0];
		final BeagleConfiguration beagleConfig = new BeagleConfiguration(this.elements, file);
		beagleConfig.setTimeout(testTimeout);
		assertThat(beagleConfig.getTimeout(), is(sameInstance(testTimeout)));

	}
}
