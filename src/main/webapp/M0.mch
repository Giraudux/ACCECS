MACHINE 
    {{ name }}

{% if (variablesInput.size + variablesOutput.size + variablesControl.size + variablesInternal.size > 0) -%}
VARIABLES

    {%- if (variablesInput.size > 0) %} 
    // input variables
    {% for variable in variablesInput %}
    {{- variable -}}
    {% if (variablesOutput.size > 0 or variablesControl.size > 0 or variablesInternal.size > 0 or loop.last == false) %}, {% endif %}
    {% endfor %}
    {%- endif %}

    {%- if (variablesOutput.size > 0) %}
    // output variables
    {% for variable in variablesOutput %}
    {{- variable -}}
    {% if (variablesControl.size > 0 or variablesInternal.size > 0 or loop.last == false) %}, {% endif %}
    {% endfor %}
    {%- endif %}

    {%- if (variablesControl.size > 0) %}
    // control variables
    {% for variable in variablesControl %}
    {{- variable -}}
    {% if (variablesInternal.size > 0 or loop.last == false) %}, {% endif %}
    {% endfor %}
    {%- endif %}

    {%- if (variablesInternal.size > 0) %} 
    // internal variables
    {% for variable in variablesInternal %}
    {{- variable -}}
    {% if (loop.last == false) %}, {% endif %}
    {% endfor %}
    {%- endif %}

{%- endif %}

{%- if (invariants.size > 0) %}
INVARIANT
    {%- for invariant in invariants %}
    {% if (loop.first) %}  {% else %}& {% endif %}
    {{- invariant -}}
    {% endfor %}
{%- endif %}

{% if (initialisations.size > 0) -%}
INITIALISATION

    {%- for initialisation in initialisations %}
    {% if (loop.first) %}   {% else %}|| {% endif %}
    {{- initialisation -}}
    {% endfor %}
    
{%- endif %}

{% if (variablesInput.size > 0 or variablesOutput.size > 0) -%}
EVENTS

    {%- if (variablesInput.size > 0) %}
    // Sense events
    {%- for i in [0..variablesInput.size-1] %}
    sense_{{variablesInput[i]}} =
        SELECT
            {{senseEvents[i]}}
        THEN
            skip //TODO
        END {%- if ( variablesOutput.size > 0 or loop.last == false) %}; {% endif %}
    {% endfor %}
    {% endif -%}
    
    {%- if (variablesOutput.size > 0) -%}
    // Reaction events
    {% for i in [0..variablesOutput.size-1] -%}
    reaction_{{variablesOutput[i]}} =
        SELECT
            {{reactionEvents[i]}}
        THEN
            skip //TODO
        END {%- if (loop.last == false) %}; {% endif %}
    {% endfor -%}
    {%- endif -%}
    
{%- endif %}
END
