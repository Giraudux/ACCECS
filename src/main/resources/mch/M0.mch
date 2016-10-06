MACHINE {{ name }}

VARIABLES
    {% for variable in variables %}
        {{ variable }}
        {% if (loop.last == false) %}
            ,
        {% endif %}
    {% endfor %}


INVARIANTS
    {% for property in properties %}
        {% if (loop.first == false) %}
            &
        {% endif %}
        {{ property }}
    {% endfor %}

INITIALISATION
    {% for initialisation in initialisations %}
        {% if (loop.first == false) %}
            ||
        {% endif %}
        {{ initialisation }}
    {% endfor %}

END
