import java.util.Arrays;

public class Manager {
    protected Repository repo;

    public Manager(Repository repo) {
        this.repo = repo;
    }

    public void add(Ticket product) {
        repo.addProduct(product);
    }

    public void removeById(int id) throws NotFoundException {
        repo.removeById(id);
    }

    public Ticket[] searchBy(String from, String to) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket: repo.findAll()) {
            if (matches(ticket, from, to)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }

    public boolean matches(Ticket ticket, String from, String to) {
        if (!ticket.getFrom().equals(from)) {
            return false;
        } if (!ticket.getTo().equals(to)) {
            return false;
        }
        return true;
    }

    public Ticket[] findAllOffers() {
        Ticket[] result = repo.getAll();
        Arrays.sort(result);
        return result;
    }
}
