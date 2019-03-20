package ru.valentingordienko.loftcoin.screens.wellcome;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import ru.valentingordienko.loftcoin.R;

public class WellComePagerAdapter extends FragmentPagerAdapter {

    private List<WellComePage> pages;

    WellComePagerAdapter(FragmentManager fm) {
        super(fm);

        pages = new ArrayList<>();
        pages.add(new WellComePage(R.drawable.image_welcome_page_1, R.string.welcome_title_1, R.string.welcome_subtitle_1));
        pages.add(new WellComePage(R.drawable.image_welcome_page_2, R.string.welcome_title_2, R.string.welcome_subtitle_2));
        pages.add(new WellComePage(R.drawable.image_welcome_page_3, R.string.welcome_title_3, R.string.welcome_subtitle_3));
    }

    @Override
    public Fragment getItem(int position) {
        return WellComeFragment.newInstance(pages.get(position));
    }

    @Override
    public int getCount() {
        return pages.size();
    }
}
