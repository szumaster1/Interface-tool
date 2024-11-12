# Interface editor

This interface is useable for the 530+ revision of runescape cache, with some edits this will also be able to load osrs caches. I released this code to help the community, I wrote this code a long time ago and just recently started with refactoring it. It works but the code isn't the best (working on it :) )

# Usage

## Startup

Open the **config.properties** file. Change the **cache_path** field to your cache path, make sure to use \\ or / and end with it aswell. After that you can launch the jar file or use your IDE to launch **InterfaceGUI.java**

> #Tool Properties

    cache_path=D:\\server\\data\\cache\\
    dump_path=dump/
    sprite_path=dump/
    version = 1

## Editing interfaces

You can select an interface on the left, once clicked on it you can selected a component from that interface on the right side.

## More info

### Font ids

List of current font ids I found
305 307 468 473 494 495 496 497 584 591 645 646 647 648 764 776 819 1591 2244 2710 3237 3793 3794 3795 4040 5419 5631 13120 13121

### Found script ids

Will update this later

### Script info

**How does those scripts work in the script tab ?**

example the transparency script : 1357;-2147483645;100;
The 'code' of the script. (not editable with this editor, you can also change the paramters it receives)

```
void script_1357(Widget widget0, int arg1) {
	widget0.setTrans(arg1);
	return;
}
```

**what do the numbers mean?**

> 1357: is the id of script, the first number in the array is ALWAYS the script id
> everything after 1357 are paremeters for that specific script.
> -2147483645 : means it's the widget itself refering to it's own hash, if you want to trigger another component use it hash.
> 100 : an extra parameter (so the trans will be 100% when hovered on the component)

### Setup info

- Step 1.
  Open the config.properties and change your path(s)

- Step 2.
  Dump your cache sprites (can be done with the editor by clicking extra --> dump sprites, if this doesn't dump everything try using steve's editor.)

- Step 3.
  Back-up your cache!!

- Step 4.
  Enjoy editing :)

#### Border info

Blue border = model (not rendered)
Red border = Container
Green border = base container
White border = Current selected component

#### Other info

Make sure you always make a back-up from your cache.

Note that cs2 stuff and models

if you want your component to render pure x & y based use position mode 0 for x & y

#### My client crashed, what did I do wrong?

Things to check :

1. Did I give a component a parent ID that doesn't exist?
2. Does the sprite exist?
3. Does the model id exist?
4. Is my color id right?
5. Did I do something to the scripts?

If these things are correct remove the component and try again, or contact me!

#### Question

My discord : Cara Shnek#6969
Rune-server : Shnek
(I will help you as much as possible but I won't make whole interfaces for you.)

#### misc info

List of current font ids

```
305 307 468 473 494 495 496 497 584 591 645 646 647 648 764 776 819 1591 2244 2710 3237 3793 3794 3795 4040 5419 5631 13120 13121
```

#### scripts

- How does those scripts work in the script tab ?

Example:

`1357;-2147483645;100;`

- full view of the script

```

void script_1357(Widget widget0, int arg1) {
widget0.setTrans(arg1);
return;
}

```

- what do the numbers mean?

`1357`: Iss the `id` of script, the first number in the array is ALWAYS the script id

everything after 1357 are paremeters

`-2147483645` : Means it's the widget itself, if you want to trigger another component use it ihash.

`100` : An extra parameter (so the trans will be 100% when hovered on the component)
