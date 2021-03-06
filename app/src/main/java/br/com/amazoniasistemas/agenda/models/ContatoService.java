package br.com.amazoniasistemas.agenda.models;

public class ContatoService {

    private final Contato contato;

    private ContatoDao contatoDao = null;

    public ContatoService(Contato contato) {
        this.contato = contato;
        this.contatoDao = new ContatoDao();
    }

    public void merge() {
        if (this.contato.key == null || this.contato.key.length() ==0) {
            this.contatoDao.insert(this.contato);
        }else{
            this.contatoDao.update(this.contato);
        }
    }

}
