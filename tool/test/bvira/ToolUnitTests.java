package bvira;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.LinkedList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ToolUnitTests {
    @Test
    public void toolHasCommandLineEntryPoint() {
        Tool.main(new String[]{});
    }

    @Test
    public void printsUsageIfNoParametersSupplied() throws UnsupportedEncodingException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Tool tool = new Tool(ps, null);
        tool.execute(Arrays.<String>asList());
        String content = baos.toString();

        assertThat(content, is(Tool.bannerText + Tool.usageInstructions));
    }

    @Test
    public void createCommandCreatesAProjectStructure() {
        FileSystem fileSystem = mock(FileSystem.class);
        Tool tool = new Tool(System.out, fileSystem);

        LinkedList<String> args = new LinkedList<String>(Arrays.asList("create", "sample"));

        tool.execute(args);

        verify(fileSystem).mkdir("sample");
        verify(fileSystem).mkdir("sample" + File.separator + "src");
        verify(fileSystem).mkdir("sample" + File.separator + "test");
        verify(fileSystem).mkdir("sample" + File.separator + "lib");
        verify(fileSystem).mkdir("sample" + File.separator + "tools");
    }
}
