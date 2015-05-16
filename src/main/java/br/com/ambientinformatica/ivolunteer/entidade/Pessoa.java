package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Pessoa {

	@Id
	@GeneratedValue(generator = "pessoa_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "pessoa_seq", sequenceName = "pessoa_seq", allocationSize = 1, initialValue = 1)
	private Integer id;
	
	private String nomePessoa;
	private String rg;
	private String orgaoExpeditor;
	private String naturalidade;
	private String cpf;
	private String nascionalidade;
	private String descricao;
	private String profissao;
	private String indicacao;
	
	private EnumSexo sexo;
	private EnumEscolaridade escolaridade;
	private EnumTipoPessoa tipoPessoa;
	private EnumEstadoCivil estadoCivil;
	private EnumTipoCasa tipoMoradia;
	private EnumNecessidadeEspeciais necessidadesEspeciais;
	private EnumFiliacao filiacao;
	private EnumSexo enumSexo;
	private EnumEscolaridade enumEscolaridade;
	
	private Date dataExpedicao;
	private Date dataNascimento;
	
	private double renda;
	private double valorAluguel;
	private int numeroDePessoasMoradia;
	
	private boolean paisVivemJuntos;
	private boolean requisitouOutraVaga;
	
	@OneToMany
	private List<Endereco> endereco = new ArrayList();
	@OneToMany
	private List<Pessoa> listaPessoaRelacionada;
	@OneToMany
	private List<Telefone> telefone;
	
	//construtor da classe
	public Pessoa(){
		endereco = new ArrayList<Endereco>();
	}


	//metodos
	public double getTotalRenda() {
		return 0;
	}

	public int getIrmaosMatriculados() {
		return 0;
	}

	public int getIdade() {
		return 0;
	}

	//metodos gets e set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoExpeditor() {
		return orgaoExpeditor;
	}

	public void setOrgaoExpeditor(String orgaoExpeditor) {
		this.orgaoExpeditor = orgaoExpeditor;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNascionalidade() {
		return nascionalidade;
	}

	public void setNascionalidade(String nascionalidade) {
		this.nascionalidade = nascionalidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getIndicacao() {
		return indicacao;
	}

	public void setIndicacao(String indicacao) {
		this.indicacao = indicacao;
	}

	public EnumSexo getSexo() {
		return sexo;
	}

	public void setSexo(EnumSexo sexo) {
		this.sexo = sexo;
	}

	public EnumEscolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(EnumEscolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public EnumTipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(EnumTipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public EnumEstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EnumEstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public EnumTipoCasa getTipoMoradia() {
		return tipoMoradia;
	}

	public void setTipoMoradia(EnumTipoCasa tipoMoradia) {
		this.tipoMoradia = tipoMoradia;
	}

	public EnumNecessidadeEspeciais getNecessidadesEspeciais() {
		return necessidadesEspeciais;
	}

	public void setNecessidadesEspeciais(
	      EnumNecessidadeEspeciais necessidadesEspeciais) {
		this.necessidadesEspeciais = necessidadesEspeciais;
	}

	public EnumFiliacao getFiliacao() {
		return filiacao;
	}

	public void setFiliacao(EnumFiliacao filiacao) {
		this.filiacao = filiacao;
	}

	public EnumSexo getEnumSexo() {
		return enumSexo;
	}

	public void setEnumSexo(EnumSexo enumSexo) {
		this.enumSexo = enumSexo;
	}

	public EnumEscolaridade getEnumEscolaridade() {
		return enumEscolaridade;
	}

	public void setEnumEscolaridade(EnumEscolaridade enumEscolaridade) {
		this.enumEscolaridade = enumEscolaridade;
	}

	public Date getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(Date dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public double getRenda() {
		return renda;
	}

	public void setRenda(double renda) {
		this.renda = renda;
	}

	public double getValorAluguel() {
		return valorAluguel;
	}

	public void setValorAluguel(double valorAluguel) {
		this.valorAluguel = valorAluguel;
	}

	public int getNumeroDePessoasMoradia() {
		return numeroDePessoasMoradia;
	}

	public void setNumeroDePessoasMoradia(int numeroDePessoasMoradia) {
		this.numeroDePessoasMoradia = numeroDePessoasMoradia;
	}

	public boolean isPaisVivemJuntos() {
		return paisVivemJuntos;
	}

	public void setPaisVivemJuntos(boolean paisVivemJuntos) {
		this.paisVivemJuntos = paisVivemJuntos;
	}

	public boolean isRequisitouOutraVaga() {
		return requisitouOutraVaga;
	}

	public void setRequisitouOutraVaga(boolean requisitouOutraVaga) {
		this.requisitouOutraVaga = requisitouOutraVaga;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}


	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}


	public Pessoa getPessoaRelacionada() {
		return pessoaRelacionada;
	}

	public void setPessoaRelacionada(Pessoa pessoaRelacionada) {
		this.pessoaRelacionada = pessoaRelacionada;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}	
}
