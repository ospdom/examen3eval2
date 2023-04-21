package base;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PrincipalTest extends Principal{


	@Test
	void testSolicitarPermiso() {
		assertFalse(solicitarPermiso(20));
        assertTrue(solicitarPermiso(90));	}

	}

//@author christian
