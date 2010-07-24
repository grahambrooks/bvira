package bvira;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DefaultFileSystemUnitTests {
    @Test
    public void canCopyFiles() {
        DefaultFileSystem fileSystem = new DefaultFileSystem();

        fileSystem.mkdir("build/sample");
        fileSystem.copy("tool/template/build.xml", "build/sample/build.xml");

        File file = new File("build/sample/build.xml");
        assertThat(file.exists(), is(true));
        assertThat(file.length(), is(124L));
    }
}
