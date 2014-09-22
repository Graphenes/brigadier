package net.minecraft.commands.tree;

import net.minecraft.commands.exceptions.IllegalCommandArgumentException;
import org.junit.Before;
import org.junit.Test;

import static net.minecraft.commands.arguments.IntegerArgumentType.integer;
import static net.minecraft.commands.builder.CommandBuilder.command;
import static net.minecraft.commands.builder.RequiredArgumentBuilder.argument;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LiteralCommandNodeTest {
    LiteralCommandNode node;

    @Before
    public void setUp() throws Exception {
        node = command("foo").build();
    }

    @Test
    public void testParse() throws Exception {
        assertThat((LiteralCommandNode) node.parse("foo"), is(node));
    }

    @Test(expected = IllegalCommandArgumentException.class)
    public void testParseInvalid() throws Exception {
        node.parse("bar");
    }

    @Test
    public void testParseChild() throws Exception {
        CommandNode child = argument("bar", integer()).build();

        node.addChild(child);

        assertThat(node.parse("foo 123"), is(child));
    }

    @Test(expected = IllegalCommandArgumentException.class)
    public void testParseNoChildren() throws Exception {
        node.parse("foo 123");
    }
}