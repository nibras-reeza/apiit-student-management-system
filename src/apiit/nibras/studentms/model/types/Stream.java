package apiit.nibras.studentms.model.types;


public enum Stream {
	Computing(Program.Bachelors),
	Business(Program.Bachelors),
	Law(Program.Bachelors),
	MSc(Program.Masters),
	MBA(Program.Masters);
	
	private Program program;
	
	Stream(Program program){
		this.program = program;
	}
	
	public Program getProgram(){
		return this.program;
	}
	
}
