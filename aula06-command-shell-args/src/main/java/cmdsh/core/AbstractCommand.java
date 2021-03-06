package cmdsh.core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractCommand implements ICommand{
	final String name;
	final IParamsParser parser;
	
	/** 
	 * @uml.property name="args"
	 * @uml.associationEnd aggregation="shared" inverse="cmdsh.core.IArgument" multiplicity="(0 -1)" 
	 */
	private final List<IArgument> args = new LinkedList<IArgument>();

	public AbstractCommand(String name, IParamsParser parser, IArgument[] arrArgs) {
		this.name = name;
		this.parser = parser;
		for (IArgument a : arrArgs) {args.add(a);}
	}
	public String getName() {
		return name;
	}
	public Iterable<IArgument> args(){
		return Collections.unmodifiableCollection(args);
	}
	@Override
	public final void performCommand(String params) {
		params = params.substring(getName().length() + 1);
		parser.parse(params, args);
		executeCommand();
	}
	protected abstract void executeCommand();
}