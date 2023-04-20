package dev.mcenv.truffle;

import com.mojang.brigadier.CommandDispatcher;
import dev.mcenv.spy.Register;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;

import static com.mojang.brigadier.arguments.StringArgumentType.*;
import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;
import static com.mojang.brigadier.builder.RequiredArgumentBuilder.argument;

public final class EvalRegister implements Register, AutoCloseable {
  private final Engine engine = Engine.create();
  private Minecraft minecraft;

  @Override
  public void apply(final CommandDispatcher<Object> dispatcher) {
    minecraft = new Minecraft(dispatcher);

    dispatcher.register(
      literal("eval")
        .then(
          argument("language", string())
            .then(
              argument("source", greedyString())
                .executes(c -> {
                  final var language = getString(c, "language");
                  final var source = getString(c, "source");

                  minecraft.setSource(c.getSource());

                  try (final var context = Context.newBuilder().engine(engine).build()) {
                    context.getBindings(language).putMember("minecraft", minecraft);
                    final var result = context.eval(language, source);
                    return result.asInt();
                  }
                })
            )
        )
    );
  }

  @Override
  public void close() {
    engine.close();
  }
}
