package dev.mcenv.truffle;

import dev.mcenv.spy.Spy;

public final class Main {
  public static void main(final String[] args) throws Throwable {
    Spy.execute(EvalCommands.class, args);
  }
}
