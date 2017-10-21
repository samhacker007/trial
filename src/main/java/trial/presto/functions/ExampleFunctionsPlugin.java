package trial.presto.functions;

import java.util.Set;

import com.facebook.presto.spi.Plugin;
import com.google.common.collect.ImmutableSet;

public class ExampleFunctionsPlugin implements Plugin {
	@Override
	public Set<Class<?>> getFunctions() {
		return ImmutableSet.<Class<?>>builder().add(ExampleNullFunction.class).add(AverageAggregation.class).build();
	}
}
