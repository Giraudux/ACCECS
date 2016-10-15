MACHINE {{ name }}

{% for variable in variables %}
    {% if (loop.first == true) %}
VARIABLES
    {% endif %}
    {{ variable }}
    {% if (loop.last == false) %}
        ,
    {% endif %}
{% endfor %}


{% for property in properties %}
    {% if (loop.first == true) %}
INVARIANTS
    {% endif %}
    {% if (loop.first == false) %}
        &
    {% endif %}
    {{ property }}
{% endfor %}

{% for initialisation in initialisations %}
    {% if (loop.first == true) %}
INITIALISATION
    {% endif %}
    {% if (loop.first == false) %}
        ||
    {% endif %}
    {{ initialisation }}
{% endfor %}

END
