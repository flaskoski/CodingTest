package flaskoski.faire.apicommunication;

import flaskoski.faire.model.Option;

public class UpdateOptionDontProcess implements IUpdateOption {
    @Override
    public void setOption(Option option, Integer quantity) {
        option.setActive(false);
    }
}
