package com.psoft.scrumboard.model.papel;

import com.psoft.scrumboard.model.papel.enums.PapelEnum;

public class ScrumMaster implements Papel {

	public PapelEnum getTipo() {
		return PapelEnum.SCRUM_MASTER;
	}
	
}