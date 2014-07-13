package celestibytes.celestialwizardry.api.spellgrammar;

import net.minecraft.entity.ai.attributes.IAttribute;

import java.util.List;

public class AttributeRune extends RuneCategory implements IAttributeRune
{

    private Object attrData;
    private IAttribute attrOfThis;

    public AttributeRune(float complexity, Object attrData, boolean takesAttribute)
    {
        super(complexity, takesAttribute, null);
        this.attrData = attrData;
    }

    @Override
    public Object getAttributeData()
    {
        return attrData;
    }

    public IAttribute getAttributeOfThisAttr()
    {
        return attrOfThis;
    }

    @Override
    public String getCategoryIDString()
    {
        return "attribute";
    }

    @Override
    public List validRuneAttributeTypes()
    {
        return null;
    }

}
