package br.com.poupexAPI.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import br.com.poupexAPI.tests.AcompanhamentoTest;
import br.com.poupexAPI.tests.AutenticacaoTest;
import br.com.poupexAPI.utils.BaseTest;



@RunWith(org.junit.runners.Suite.class)
@SuiteClasses({
	AutenticacaoTest.class,
	AcompanhamentoTest.class	
})
public class SuiteTest extends BaseTest{

}
