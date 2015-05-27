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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private String certidaoNascimento;
	
	private EnumSexo sexo;
	private EnumEscolaridade escolaridade;
	private EnumTipoPessoa tipoPessoa;
	private EnumEstadoCivil estadoCivil;
	private EnumTipoCasa tipoMoradia;
	private String necessidadesEspeciais;
	private EnumFiliacao filiacao;
	private EnumSexo enumSexo;
	private EnumEscolaridade enumEscolaridade;
	private EnumTipoTelefone enumTipoPessoa;
	
	@Temporal(TemporalType.DATE)
	private Date dataExpedicao;
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	//data de vencimento criada devido o documento de cadastro de candidato ter validade de 6 meses
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	private double renda;
	private double valorAluguel;
	private int numeroDePessoasMoradia;
	
	private boolean paisVivemJuntos;
	private boolean requisitouOutraVaga;
	
	@OneToMany
	private List<Endereco> listaEndereco = new ArrayList<Endereco>();
	@OneToMany
	private List<Pessoa> listaPessoaRelacionada;
	@OneToMany
	private List<Telefone> listaTelefone = new ArrayList<Telefone>();
	
	//construtor da classe
	public Pessoa(){
		listaEndereco = new ArrayList<Endereco>();
		listaTelefone = new ArrayList<Telefone>();
	}


	public double getTotalRenda() {
		return 0;
	}

	public int getIrmaosMatriculados() {
		return 0;
	}

	public int getIdade() {
		return 0;
	}

	public void addTelefone(Telefone telefone){
		if(!this.listaTelefone.contains(telefone)){
			this.listaTelefone.add(telefone);
		}
	}
	
	public void addEndereco(Endereco endereco){
		if(!this.listaEndereco.contains(endereco)){
			this.listaEndereco.add(endereco);
		}
	}
	
	public void addPessoa(Pessoa pessoaRelacionada){
		if(!this.listaPessoaRelacionada.contains(pessoaRelacionada)){
			this.listaPessoaRelacionada.add(pessoaRelacionada);
		}
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

	public List<Pessoa> getListaPessoaRelacionada() {
		return listaPessoaRelacionada;
	}


	public void setListaPessoaRelacionada(List<Pessoa> listaPessoaRelacionada) {
		this.listaPessoaRelacionada = listaPessoaRelacionada;
	}

	public String getCertidaoNascimento() {
		return certidaoNascimento;
	}


	public void setCertidaoNascimento(String certidaoNascimento) {
		this.certidaoNascimento = certidaoNascimento;
	}


	public EnumTipoTelefone getEnumTipoPessoa() {
		return enumTipoPessoa;
	}


	public void setEnumTipoPessoa(EnumTipoTelefone enumTipoPessoa) {
		this.enumTipoPessoa = enumTipoPessoa;
	}


	public Date getDataVencimento() {
		return dataVencimento;
	}


	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}


	public List<Endereco> getListaEndereco() {
		return listaEndereco;
	}


	public void setListaEndereco(List<Endereco> listaEndereco) {
		this.listaEndereco = listaEndereco;
	}


	public List<Telefone> getListaTelefone() {
		return listaTelefone;
	}


	public void setListaTelefone(List<Telefone> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}


	public void setNecessidadesEspeciais(String necessidadesEspeciais) {
		this.necessidadesEspeciais = necessidadesEspeciais;
	}


	public String getNecessidadesEspeciais() {
		return necessidadesEspeciais;
	}
}
