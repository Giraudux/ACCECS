MACHINE 
	{{ name }}
{% if (variablesInput.size + variablesOutput.size + variablesControl.size + variablesInternal.size > 0) %}
VARIABLES
	{% if (variablesInput.size > 0) %}// input variables{% endif %}
    {% for variable in variablesInput %}{{ variable }}{% if (variablesOutput.size > 0 or variablesControl.size > 0 or variablesInternal.size > 0 or loop.last == false) %}, {% endif %}
    {% endfor %}
    {% if (variablesOutput.size > 0) %}// output variables{% endif %}
    {% for variable in variablesOutput %}{{ variable }}{% if (variablesControl.size > 0 or variablesInternal.size > 0 or loop.last == false) %}, {% endif %}
    {% endfor %}
    {% if (variablesControl.size > 0) %}// control variables{% endif %}
    {% for variable in variablesControl %}{{ variable }}{% if (variablesInternal.size > 0 or loop.last == false) %}, {% endif %}
    {% endfor %}
    {% if (variablesInternal.size > 0) %}// internal variables{% endif %}
    {% for variable in variablesInternal %}{{ variable }}{% if (loop.last == false) %}, {% endif %}
    {% endfor %}{% endif %}
{% if (properties.size > 0) %}PROPERTIES
    {% for property in properties %}{% if (loop.first) %}  {% else %}& {% endif %}{{ property }}
    {% endfor %}{% endif %}
{% if (invariants.size > 0) %}INVARIANT
    {% for invariant in invariants %}{% if (loop.first) %}  {% else %}& {% endif %}{{ invariant }}
    {% endfor %}{% endif %}
{% if (initialisations.size > 0) %}
INITIALISATION
    {% for initialisation in initialisations %}{% if (loop.first) %}   {% else %}|| {% endif %}{{ initialisation }}
    {% endfor %}
{% endif %}
{% if (variablesInput.size > 0 or variablesOutput.size > 0) %}EVENTS

    {% if (variablesOutput.size > 0) %}// Sense events {% endif %}{% for variable in variablesInput %}
    sense_{{variable}} =
		SELECT
		    {{variable}} = {{variable}}
		THEN
		    {{variable}} := {{variable}}
		END;
    {% endfor %}
    {% if (variablesOutput.size > 0) %}// Monitor events {% endif %} {% for variable in variablesOutput %}
    monitor_{{variable}} =
		SELECT
	    	{{variable}} = {{variable}}
		THEN
	    	{{variable}} := {{variable}} 
		END; 
    {% endfor %} {% endif %}
END
