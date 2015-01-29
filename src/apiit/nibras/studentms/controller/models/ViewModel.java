package apiit.nibras.studentms.controller.models;

import java.util.List;

public abstract class ViewModel {

	public abstract List<String> validate();

	public abstract Object toObject();
}
