package flaskoski.faire.apicommunication;

import flaskoski.faire.model.Option;

public class UpdateOptionProcess implements IUpdateOption {
    @Override
    public void setOption(Option opt, Integer quantity) {
        opt.setAvailable_quantity(opt.getAvailable_quantity() - quantity);
    }
}
