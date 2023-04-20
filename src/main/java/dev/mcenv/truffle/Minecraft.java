package dev.mcenv.truffle;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import org.graalvm.polyglot.HostAccess;

public final class Minecraft {
  private final CommandDispatcher<Object> dispatcher;
  private Object source;

  public Minecraft(final CommandDispatcher<Object> dispatcher) {
    this.dispatcher = dispatcher;
  }

  @HostAccess.Export
  public int execute(final String input) throws CommandSyntaxException {
    return dispatcher.execute(input, source);
  }

  public void setSource(final Object source) {
    this.source = source;
  }
}
